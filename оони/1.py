import heapq
import os
from collections import defaultdict

class HuffmanNode:
    def __init__(self, char, freq):
        self.char = char
        self.freq = freq
        self.left = None
        self.right = None

    def __lt__(self, other):
        return self.freq < other.freq

def build_frequency_table(image):
    frequency = defaultdict(int)
    for pixel in image:
        frequency[pixel] += 1
    return frequency

def build_huffman_tree(frequency):
    priority_queue = [HuffmanNode(char, freq) for char, freq in frequency.items()]
    heapq.heapify(priority_queue)
    
    while len(priority_queue) > 1:
        left_child = heapq.heappop(priority_queue)
        right_child = heapq.heappop(priority_queue)
        
        merged_node = HuffmanNode(None, left_child.freq + right_child.freq)
        merged_node.left = left_child
        merged_node.right = right_child
        
        heapq.heappush(priority_queue, merged_node)
        
    return priority_queue[0]

def build_codeword_table(root, prefix="", codeword_table={}):
    if root:
        if root.char is not None:
            codeword_table[root.char] = prefix
        build_codeword_table(root.left, prefix + "0", codeword_table)
        build_codeword_table(root.right, prefix + "1", codeword_table)
    return codeword_table

def compress_image(image, codeword_table):
    compressed_data = ""
    for pixel in image:
        compressed_data += codeword_table[pixel]
    return compressed_data

def pad_encoded_data(compressed_data):
    padded_length = 8 - (len(compressed_data) % 8)
    padded_data = compressed_data + "0" * padded_length
    padded_data = "{0:08b}".format(padded_length) + padded_data
    return padded_data

def convert_binary_to_bytes(padded_data):
    bytes_array = bytearray()
    for i in range(0, len(padded_data), 8):
        byte = padded_data[i:i+8]
        bytes_array.append(int(byte, 2))
    return bytes_array

def compress_image_huffman(image):
    frequency = build_frequency_table(image)
    huffman_tree_root = build_huffman_tree(frequency)
    codeword_table = build_codeword_table(huffman_tree_root)
    compressed_data = compress_image(image, codeword_table)
    padded_data = pad_encoded_data(compressed_data)
    bytes_array = convert_binary_to_bytes(padded_data)
    return bytes_array, codeword_table

def calculate_file_size(compressed_data):
    num_bits = len(compressed_data) * 8
    num_bytes = num_bits // 8  # Округляем до целого числа байтов
    return num_bytes

# Пример использования
image = [0, 1, 0, 1, 1, 1, 0, 0]  # Пример изображения в виде последовательности пикселей (0 и 1)
compressed_image, codeword_table = compress_image_huffman(image)
print("Сжатые данные (в виде массива байт):", list(compressed_image))
print("Таблица кодирования:", codeword_table)

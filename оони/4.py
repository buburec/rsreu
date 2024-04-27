import time

# Record start time
start_time = time.time()

from PIL import Image
import io

def compress_lz(data):
    compressed_data = []
    dictionary = {}
    buffer = b""

    for byte in data:
        buffer += bytes([byte])
        if buffer not in dictionary:
            index = len(dictionary)
            dictionary[buffer] = index
            compressed_data.append((0, 0, byte))
            buffer = b""
        elif len(buffer) == len(data):
            index = dictionary[buffer]
            compressed_data.append((index, 0, 0))
        else:
            continue

    return compressed_data

def decompress_lz(compressed_data):
    decompressed_data = bytearray()
    dictionary = {}

    for entry in compressed_data:
        index, length, byte = entry
        if index == 0:
            decompressed_data.append(byte)
            dictionary[len(dictionary)] = bytes([byte])
        else:
            sequence = dictionary[index] + bytes([byte])
            decompressed_data += sequence
            dictionary[len(dictionary)] = sequence

    return decompressed_data

# Открываем изображение
image = Image.open("example3.jpg")

# Преобразуем изображение в байтовую строку
with io.BytesIO() as output:
    image.save(output, format='JPEG')
    image_data = output.getvalue()

# Сжимаем изображение
compressed_image = compress_lz(image_data)

# Декомпрессия изображения
# decompressed_image_data = decompress_lz(compressed_image)

# Преобразуем декомпрессированные данные в изображение
# decompressed_image = Image.open(io.BytesIO(decompressed_image_data))


# # Сохраняем декомпрессированное изображение
# decompressed_image.save("decompressed_image.jpg")

print(len(compressed_image))
print("Compression ratio:", len(compressed_image) / len(image_data))

# Record end time
end_time = time.time()

# Calculate execution time
execution_time = end_time - start_time
print("Execution time:", execution_time, "seconds")
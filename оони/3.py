import time

# Record start time
start_time = time.time()

from PIL import Image

def compress_image_rle(image_path):
    image = Image.open(image_path)
    width, height = image.size
    pixels = list(image.getdata())
    compressed_data = []
    current_pixel = pixels[0]
    count = 0
    
    for pixel in pixels:
        if pixel == current_pixel:
            count += 1
        else:
            compressed_data.append((current_pixel, count))
            current_pixel = pixel
            count = 1
    
    compressed_data.append((current_pixel, count))
    
    return compressed_data, width, height

def encode_rle(compressed_data):
    encoded_data = bytearray()
    for pixel, count in compressed_data:
        # Преобразуем цвет пикселя в байты и добавляем их в массив
        encoded_data.append(pixel[0])  # Добавляем значение цвета красного канала
        encoded_data.append(pixel[1])  # Добавляем значение цвета зеленого канала
        encoded_data.append(pixel[2])  # Добавляем значение цвета синего канала
        
        # Разбиваем значение count на два байта и добавляем их в массив
        encoded_data.append((count >> 8) & 0xFF)  # Старший байт (биты 8-15)
        encoded_data.append(count & 0xFF)         # Младший байт (биты 0-7)
    return encoded_data

def save_compressed_image(encoded_data, width, height, output_path):
    with open(output_path, 'wb') as file:
        file.write(bytes([width >> 8, width & 0xFF]))
        file.write(bytes([height >> 8, height & 0xFF]))
        file.write(encoded_data)


# Пример использования
image_path = "example3.jpg"  # Путь к вашему изображению
output_path = "compressed_image.rle"  # Путь для сохранения сжатого изображения

compressed_data, width, height = compress_image_rle(image_path)
encoded_data = encode_rle(compressed_data)
save_compressed_image(encoded_data, width, height, output_path)


# Record end time
end_time = time.time()

# Calculate execution time
execution_time = end_time - start_time
print("Execution time:", execution_time, "seconds")
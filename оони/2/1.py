import zlib
from PIL import Image

def compress_image(input_image_path, output_image_path):
    """
    Сжимает изображение с использованием алгоритма DEFLATE.

    :param input_image_path: Путь к исходному изображению
    :param output_image_path: Путь к сжатому изображению
    """
    # Загружаем изображение
    image = Image.open(input_image_path)

    # Преобразуем изображение в байтовый формат
    image_bytes = image.tobytes()

    # Сжимаем изображение с использованием алгоритма DEFLATE
    compressed_image = zlib.compress(image_bytes)

    # Сохраняем сжатое изображение
    with open(output_image_path, "wb") as f:
        f.write(compressed_image)


def decompress_image(input_image_path, output_image_path):
    """
    Распаковывает сжатое изображение обратно в его исходный формат.

    :param input_image_path: Путь к сжатому изображению
    :param output_image_path: Путь к восстановленному изображению
    """
    # Загружаем сжатое изображение
    with open(input_image_path, "rb") as f:
        compressed_image = f.read()

    # Распаковываем данные с использованием алгоритма DEFLATE
    decompressed_image = zlib.decompress(compressed_image)

    # Преобразуем байтовые данные обратно в изображение
    image = Image.frombytes("RGB", (512, 512), decompressed_image)  # Введите размер изображения

    # Сохраняем восстановленное изображение
    image.save(output_image_path)        

# Пример использования
input_image_path = "1.png"
output_image_path = "compressed_image.deflate"
output = "copy.png"
compress_image(input_image_path, output_image_path)
decompress_image(output_image_path, output)

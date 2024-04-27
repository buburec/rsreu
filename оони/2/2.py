from skimage import io, color, transform
import numpy as np

def compress_image(input_image_path, output_image_path, quality=50):
    """
    Сжимает изображение с использованием алгоритма JPEG.

    :param input_image_path: Путь к исходному изображению
    :param output_image_path: Путь к сжатому изображению
    :param quality: Качество сжатия (от 0 до 100, чем выше, тем лучше качество)
    """
    # Загрузка изображения
    image = io.imread(input_image_path)

    # Преобразование RGB в YCbCr (цветовая модель, используемая в JPEG)
    ycbcr_image = color.rgb2ycbcr(image)

    # Применение DCT к каналам Cb и Cr
    dct_cb = _block_dct(ycbcr_image[:, :, 1])
    dct_cr = _block_dct(ycbcr_image[:, :, 2])

    # Определение порога квантования на основе выбранного качества
    quantization_table = _generate_quantization_table(quality)

    # Квантование коэффициентов DCT
    quantized_cb = np.round(dct_cb / quantization_table)
    quantized_cr = np.round(dct_cr / quantization_table)

    # Обратное квантование
    dequantized_cb = quantized_cb * quantization_table
    dequantized_cr = quantized_cr * quantization_table

    # Обратное преобразование DCT
    reconstructed_cb = _block_idct(dequantized_cb)
    reconstructed_cr = _block_idct(dequantized_cr)

    # Объединение компонент Y, Cb и Cr и преобразование обратно в RGB
    compressed_image = np.zeros_like(image)
    compressed_image[:, :, 0] = ycbcr_image[:, :, 0]
    compressed_image[:, :, 1] = reconstructed_cb
    compressed_image[:, :, 2] = reconstructed_cr
    compressed_image = np.clip(color.ycbcr2rgb(compressed_image), 0, 1)

    # Сохранение сжатого изображения
    io.imsave(output_image_path, compressed_image)

def _block_dct(channel):
    """
    Применяет дискретное косинусное преобразование к блокам 8x8 канала.

    :param channel: Канал изображения
    :return: Канал с примененным DCT
    """
    # Разбиение канала на блоки 8x8
    blocks = transform.block_reduce(channel, (8, 8), np.mean)
    
    # Применение DCT к каждому блоку
    dct_blocks = np.zeros_like(blocks)
    for i in range(blocks.shape[0]):
        for j in range(blocks.shape[1]):
            dct_blocks[i, j] = transform.dctn(blocks[i, j], norm='ortho')

    return dct_blocks

def _block_idct(blocks):
    """
    Применяет обратное дискретное косинусное преобразование к блокам.

    :param blocks: Блоки с коэффициентами DCT
    :return: Канал с обратным DCT
    """
    # Применение обратного DCT к каждому блоку
    channel = np.zeros((blocks.shape[0]*8, blocks.shape[1]*8))
    for i in range(blocks.shape[0]):
        for j in range(blocks.shape[1]):
            channel[i*8:(i+1)*8, j*8:(j+1)*8] = transform.idctn(blocks[i, j], norm='ortho')

    return channel

def _generate_quantization_table(quality=50):
    """
    Генерирует квантовую таблицу на основе заданного качества сжатия.

    :param quality: Качество сжатия (от 0 до 100, чем выше, тем лучше качество)
    :return: Квантовая таблица
    """
    quality = max(1, min(quality, 100))
    if quality < 50:
        scale = 50 / quality
    else:
        scale = 2 - quality / 50

    # Базовая квантовая таблица JPEG
    base_table = np.array([[16, 11, 10, 16, 24, 40, 51, 61],
                           [12, 12, 14, 19, 26, 58, 60, 55],
                           [14, 13, 16, 24, 40, 57, 69, 56],
                           [14, 17, 22, 29, 51, 87, 80, 62],
                           [18, 22, 37, 56, 68, 109, 103, 77],
                           [24, 35, 55, 64, 81, 104, 113, 92],
                           [49, 64, 78, 87, 103, 121, 120, 101],
                           [72, 92, 95, 98, 112, 100, 103, 99]])

    # Масштабирование таблицы на основе выбранного качества
    quantization_table = np.round(scale * base_table)
    quantization_table[quantization_table < 1] = 1

    return quantization_table.astype(int)

# Пример использования
input_image_path = "1.jpg"
output_image_path = "compressed_image.jpg"
compress_image(input_image_path, output_image_path, quality=50)

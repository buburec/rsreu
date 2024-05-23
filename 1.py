from PyQt6 import uic
from PyQt6.QtCore import Qt, QThread, pyqtSignal, pyqtSlot
from PyQt6.QtWidgets import QApplication, QMainWindow, QFileDialog
from PyQt6.QtGui import QPixmap
import sys
import easyocr
from untitled_ui import Ui_MainWindow
from autocorrect import Speller
from openai import OpenAI
import re
import matplotlib.pyplot as plt
import speech_recognition as sr
from PyQt6.QtWidgets import QApplication, QMessageBox

class VoiceRecognitionWorker(QThread):
    result_ready = pyqtSignal(str)
    error_occurred = pyqtSignal(str)


    def run(self):
        recognizer = sr.Recognizer()
        with sr.Microphone() as microphone:
            recognizer.adjust_for_ambient_noise(microphone, 2)
            try:
                audio = recognizer.listen(microphone, 5, 5)
                text = recognizer.recognize_google(audio, language="ru-RU")
                self.result_ready.emit(text)
            except Exception as e:
                self.error_occurred.emit(str(e))


class FigureCreator(QMainWindow, Ui_MainWindow):
    task_path = "task.png"
    client = OpenAI(
        api_key = "sk-l1oFWROXcm6tQTelXG1o9HaJssjF4D28",
        base_url = "https://api.proxyapi.ru/openai/v1",
    )    
    begin_of_request = "Распознай о каких фигурах идет речь в задаче и выпиши их с массивами координат. Ответ предоставь по следующему примеру Имя фигуры: треугольник Массив координат: x = [10, 20, 30] y = [100, 150, 50]. Определить фигуру нужно в любом случае и дать толькое ее название и массивы состоящие из чисел, решать задачу не нужно. И не ставь запятую между массивами. Текст задачи:"
    
    
    def __init__(self):
        super().__init__()
        uic.loadUi('untitled.ui', self)
        self.pushButton.clicked.connect(self.choose_task)
        self.pushButton_draw.clicked.connect(self.draw)
        self.pushButton_voice.clicked.connect(self.voice_assistant)
        self.pushButton_recognize.clicked.connect(self.text_recognition)
        self.pushButton_correct.clicked.connect(self.correct)
        pixmap = QPixmap("upload.png")
        pixmap = pixmap.scaledToWidth(100)
        self.label.setAlignment(Qt.AlignmentFlag.AlignCenter)
        self.label.setPixmap(pixmap)
        pixmap = QPixmap("figure.png")
        pixmap = pixmap.scaledToWidth(400)
        self.label_figure.setAlignment(Qt.AlignmentFlag.AlignCenter)
        self.label_figure.setPixmap(pixmap)


    def correct(self):
        spell = Speller('ru')
        self.textEdit.setText(spell(self.textEdit.toPlainText()))


    def create_request(self):
        chat_completion = self.client.chat.completions.create(
            model="gpt-3.5-turbo", 
            messages=[{"role": "user", "content": self.begin_of_request + self.textEdit.toPlainText()}]
        )
        return chat_completion.choices[0].message.content


    def parse_shape_and_coordinates(self, string):
        shape_match = re.search(r'Имя фигуры: (\w+)', string)
        if shape_match:
            shape = shape_match.group(1)
        else:
            shape = "Неизвестная фигура"
        coordinates_match = re.search(r'Массив координат: x = \[(.*?)\] y = \[(.*?)\]', string) or re.search(r'Массив координат: x = \[(.*?)\], y = \[(.*?)\]', string)
        x_coordinates = []
        y_coordinates = []
        if coordinates_match:
            try:
                x_coordinates = list(map(int, coordinates_match.group(1).split(',')))
                y_coordinates = list(map(int, coordinates_match.group(2).split(',')))
            except:
                QMessageBox.critical(None, "Ошибка", "Не удалось построить фигуру")
        else:
            QMessageBox.critical(None, "Ошибка", "Не удалось построить фигуру")
        return shape, x_coordinates, y_coordinates
                    

    def voice_assistant(self):
        self.textEdit.setText("Идет запись...")
        self.voice_thread = VoiceRecognitionWorker()
        self.voice_thread.result_ready.connect(self.on_voice_result_ready)
        self.voice_thread.error_occurred.connect(self.on_voice_error_occurred)
        self.voice_thread.start()


    @pyqtSlot(str)
    def on_voice_result_ready(self, text):
        self.textEdit.setText(text)


    @pyqtSlot(str)
    def on_voice_error_occurred(self, error_message):
        QMessageBox.critical(self, "Ошибка", f"Не удалось распознать текст: {error_message}")


    def draw(self):
        request = self.create_request()
        name, x, y = self.parse_shape_and_coordinates(request)
        self.plot_shape(name, x, y)
        pixmap = QPixmap("result.png")
        self.label_figure.setPixmap(pixmap)
        
        
    def plot_shape(self, shape_name, x, y):
        try:
            plt.figure()
            print(x, y)
            plt.plot(x + [x[0]], y + [y[0]], marker='') 
            for i, (xi, yi) in enumerate(zip(x, y)):
                plt.text(xi, yi, f'{chr(65+i)}') 
            plt.xlabel('x')
            plt.ylabel('y')
            plt.title(shape_name)
            plt.grid(True)
            plt.savefig("result.png")
        except Exception as e:
            QMessageBox.critical(None, "Ошибка", "Не удалось построить фигуру")

            
    def choose_task(self):
        file_name, _ = QFileDialog.getOpenFileName(self, "Выбрать изображение", "", "Images (*.png *.jpg *.jpeg *.bmp *.gif)")
        if file_name:
            pixmap = QPixmap(file_name)
            pixmap = pixmap.scaledToWidth(400)
            self.label.setPixmap(pixmap)
            self.task_path = file_name
            

    def text_recognition(self):
        file_name = self.task_path
        reader = easyocr.Reader(["ru", "en"])
        result = reader.readtext(file_name, detail=0, paragraph=True)
        result = '\n'.join(result)
        self.textEdit.setText(result)
    

if __name__ == '__main__':
    application = QApplication(sys.argv)
    executable = FigureCreator()
    # executable.show()
    executable.showMaximized()
    sys.exit(application.exec())

from PyQt6 import uic
from PyQt6.QtWidgets import QApplication, QMainWindow, QFileDialog
from PyQt6.QtGui import QPixmap 
import sys
import easyocr
from untitled_ui import Ui_MainWindow

class Lab4(QMainWindow, Ui_MainWindow):

    def __init__(self):
        super().__init__()
        uic.loadUi('untitled.ui', self)
        self.pushButton.clicked.connect(self.push)

    def push(self):
        file_name, _ = QFileDialog.getOpenFileName(self, "Выбрать изображение", "", "Images (*.png *.jpg *.jpeg *.bmp *.gif)")
        if file_name:
            try:
                pixmap = QPixmap(file_name)
                pixmap = pixmap.scaledToWidth(300)
                self.label.setPixmap(pixmap)
                recognized_text = self.text_recognition(file_name)
                recognized_text = '\n'.join(recognized_text)
                self.textEdit.setText(recognized_text)
            except Exception as e:
                print("Ошибка при загрузке изображения:", e)

    def text_recognition(self, file_path):
        reader = easyocr.Reader(["ru", "en"])
        result = reader.readtext(file_path, detail=0, paragraph=True)
        return result      
         
if __name__ == '__main__':
    application = QApplication(sys.argv)
    executable = Lab4()
    executable.show()
    sys.exit(application.exec())

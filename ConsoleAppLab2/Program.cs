using ConsoleAppLab2.FileService;
using ConsoleAppLab2.LexerService;

namespace ConsoleAppLab2
{
    public class Program
  {
    public static void Main(string[] args)
    {
      if (args.Length != 3)
      {
        Console.WriteLine("Ошибка: недостаточное количество входных параметров");
        return;
      }

      FileReader fileReader = FileReader.GetInstance();
      string inputFilename = args[0];
      string? input = fileReader.ReadText(inputFilename);
      if (input == null || input.Equals(string.Empty))
      {
        Console.WriteLine("Ошибка: входной файл пуст");
        return;
      }

      Lexer lexer = Lexer.GetInstance();
      
      lexer.Apply(input);

      FileWriter fileWriter = FileWriter.GetInstance();
      string outputTokens = args[1];
      fileWriter.WriteText(outputTokens, lexer.Tokens);

      string outputSymbols = args[2];
      fileWriter.WriteText(outputSymbols, lexer.Symbols);

      Console.WriteLine("Работа лексического анализатора завершена");
    }
  }
}


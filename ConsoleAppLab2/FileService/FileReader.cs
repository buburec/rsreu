using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleAppLab2.FileService
{
  public class FileReader
  {
    private static FileReader? _instance = null;

    private FileReader() { }

    public static FileReader GetInstance()
    {
      _instance ??= new();
      return _instance;
    }

    public string? ReadText(string parInputFileName)
    {
      try
      {
        return File.ReadAllText(parInputFileName);
      }
      catch (Exception ex)
      {
        Console.WriteLine(ex.Message);
      }
      return null;
    }
  }
}

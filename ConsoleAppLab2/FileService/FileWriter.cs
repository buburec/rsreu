using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ConsoleAppLab2.Collection;

namespace ConsoleAppLab2.FileService
{
  public class FileWriter
  {
    private static FileWriter? _instance = null;

    private FileWriter() { }

    public static FileWriter GetInstance()
    {
      _instance ??= new();
      return _instance;
    }

    public void WriteText(string parOutputFileName, LexerCollection parCollection)
    {
      try
      {
        File.WriteAllLines(parOutputFileName, parCollection.Collection);
      }
      catch (Exception ex)
      {
        Console.WriteLine(ex.ToString());
      }
    }
  }
}

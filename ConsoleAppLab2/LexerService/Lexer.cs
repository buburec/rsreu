using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ConsoleAppLab2.Collection;

namespace ConsoleAppLab2.LexerService
{
  public class Lexer
  {
    private int _idCounter = 0;
    private readonly LexerCollection? _tokens = null;
    private readonly LexerCollection? _symbols = null;
    private static Lexer? _instance = null;

    public LexerCollection? Tokens { get { return _tokens; } }
    public LexerCollection? Symbols { get { return _symbols; } }

    private Lexer() 
    {
      _tokens = new();
      _symbols = new();
    }

    public static Lexer GetInstance()
    {
      _instance ??= new();
      return _instance;
    }

    public void Apply(string parInput)
    {
      int position = 0;
      while (position < parInput.Length) 
      {
        char symbol = parInput[position];
        if (LexerChecker.IsIdentifierHead(symbol))
        {
          ReadIdentifier(parInput, ref position);
        }
        else if (LexerChecker.IsDigit(symbol))
        {
          ReadNumber(parInput, ref position);
        }
        else if (LexerChecker.IsOperator(symbol))
        {
          ReadOperator(parInput, ref position);
        }
        else if (LexerChecker.IsSpace(symbol))
        {
          position++;
        }
        else
        {
          ReadWrongSymbol(parInput, ref position);
        }
      }
    }

    private void ReadIdentifier(string parInput, ref int parPosition) 
    {
      int start = parPosition++;
      while (parPosition < parInput.Length && LexerChecker.IsIdentifier(parInput[parPosition]))
      {
        parPosition++;
      }
      string identifier = parInput.Substring(start, parPosition - start);
      if (char.IsDigit(identifier[0]))
      {
        _tokens.Add($"Лексическая ошибка! Идентификатор «{identifier}» не может начинаться с цифры на позиции {start + 1}");
        return;
      }
      _tokens.Add($"<id,{++_idCounter}> - идентификатор с именем {identifier}");
      _symbols.Add($"{_idCounter} - {identifier}");
    }

    private void ReadNumber(string parInput, ref int parPosition)
    {
      int start = parPosition;
      bool hasDot = false;
      int faultPosition = 0;
      while (parPosition < parInput.Length && LexerChecker.IsDigit(parInput[parPosition]))
      {
        if (parInput[parPosition] == '.')
        {
          if (hasDot)
          {
            faultPosition = parPosition;
          }
          hasDot = true;
        }
        parPosition++;
      }
      if (LexerChecker.IsIdentifier(parInput[parPosition]))
      {
        parPosition = start;
        ReadIdentifier(parInput, ref parPosition);
        return;
      }
      string number = parInput.Substring(start, parPosition - start);
      if (faultPosition > 0)
      {
        _tokens.Add($"Лексическая ошибка! Неправильно задана константа «{number}» на позиции {faultPosition}");
      }
      else if (hasDot && number.Length > 0)
      {
        _tokens.Add($"<{number}> - константа вещественного типа");
      }
      else if (number.Length > 0)
      {
        _tokens.Add($"<{number}> - константа целого типа");
      }
      else
      {
        _tokens.Add($"Лексическая ошибка! Недопустимый символ \"{number}\" на позиции {parPosition - 1}");
      }
    }

    private void ReadOperator(string parInput, ref int parPosition)
    {
      switch (parInput[parPosition++])
      {
        case '+':
          _tokens.Add($"<+> - операция сложения");
          break;
        case '-':
          _tokens.Add($"<-> - операция вычитания");
          break;
        case '*':
          _tokens.Add($"<*> - операция умножения");
          break;
        case '/':
          _tokens.Add($"</> - операция деления");
          break;
        case '(':
          _tokens.Add($"<(> - открывающая скобка");
          break;
        case ')':
          _tokens.Add($"<)> - закрывающая скобка");
          break;
      }
    }

    private void ReadWrongSymbol(string parInput, ref int parPosition)
    {
      _tokens.Add($"Лексическая ошибка! Недопустимый символ \"{parInput[parPosition]}\" на позиции {parPosition++}");
    }
  }
}

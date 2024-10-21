using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleAppLab2.LexerService
{
  public static class LexerChecker
  {
    public static bool IsIdentifierHead(char parSymbol)
    {
      return parSymbol == '_' || char.IsLetter(parSymbol);
    }

    public static bool IsIdentifier(char parSymbol)
    {
      return parSymbol == '_' || char.IsLetterOrDigit(parSymbol);
    }

    public static bool IsDigit(char parSymbol)
    {
      return char.IsDigit(parSymbol) || parSymbol == '.';
    }

    public static bool IsOperator(char parSymbol)
    {
      return "+-*/()".Contains(parSymbol);
    }

    public static bool IsSpace(char parSymbol)
    {
      return parSymbol == ' ';
    }
  }
}

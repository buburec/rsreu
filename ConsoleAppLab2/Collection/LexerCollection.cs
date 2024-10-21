using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleAppLab2.Collection
{
  public class LexerCollection
  {
    private readonly List<string> _collection = [];

    public List<string> Collection { get { return _collection; } }

    public LexerCollection() { }

    public void Add(string token)
    {
      _collection.Add(token);
    }
  }
}

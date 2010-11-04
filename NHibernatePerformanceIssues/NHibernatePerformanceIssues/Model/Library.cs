using System;
using System.Collections.Generic;
using Spring.Util;
using System.Linq;

namespace eu.podval.NHibernatePerformanceIssues.Model {
    public class Library {
        public String Name { get; private set; }
        public String Address { get; set; }
        public String Director { get; set; }

        private ICollection<Book> books = new List<Book>();

        public Library(string name) {
            AssertUtils.IsTrue(!string.IsNullOrEmpty(name));
            Name = name;
        }

        public void AddBook(Book book) {
            books.Add(book);
        }

        public void RemoveBook(String isbn) {
            Book book = books.Where(b => b.Isbn.Equals(isbn)).SingleOrDefault();
            AssertUtils.IsTrue(book != null, "Can't remove the book, the library doesn't own book for isbn [" + isbn + "].");
            if (book != null) {
                books.Remove(book);
            }
        }
    }
}

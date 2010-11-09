using System;
using System.Collections.Generic;
using Spring.Util;
using System.Linq;

namespace eu.podval.NHibernatePerformanceIssues.Model {
    public class Library {
        private Guid id;
        public Guid Id {
            get { return id; }
            private set { id = value; }
        }

        public String Name { get; private set; }
        public String Address { get; set; }
        public String Director { get; set; }

        private ICollection<Book> books = new List<Book>();

        /// <summary>
        /// Empty private constructor for persistence.
        /// </summary>
        private Library() {
        }

        /// <summary>
        /// Default constructor using library name <paramref name="name"/>, 
        /// assigning new <see cref="Guid"/> becuase entity live also un-persisted.
        /// </summary>
        /// <param name="name"></param>
        public Library(string name) {
            AssertUtils.IsTrue(!string.IsNullOrEmpty(name));
            Id = Guid.NewGuid();
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

        public IEnumerable<Book> Books {
            get { return books; }
        }
    }
}

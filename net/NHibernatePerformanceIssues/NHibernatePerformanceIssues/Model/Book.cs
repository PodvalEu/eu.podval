using System;
using System.Collections.Generic;
using Spring.Util;
using System.Linq;

namespace eu.podval.NHibernatePerformanceIssues.Model {
    public class Book {
        private Guid id;
        public Guid Id {
            get { return id; }
            private set { id = value; }
        }
        
        public String Name { get; set; }
        public String Author { get; set; }
        public int Pages { get; set; }

        public String Isbn { get; private set; }
        public Library Library { get; private set; }

        private ICollection<Rental> rentals = new List<Rental>();

        /// <summary>
        /// Empty private constructor for persistence.
        /// </summary>
        private Book() {
        }

        /// <summary>
        /// Default constructor assigning new <see cref="Guid"/> becuase entity live also un-persisted.
        /// </summary>
        public Book(Library library, string isbn) {
            AssertUtils.IsTrue(!string.IsNullOrEmpty(isbn));
            AssertUtils.IsTrue(library != null, "Library is mandatory for book construction.");

            Id = Guid.NewGuid();
            Isbn = isbn;
            Library = library;
            library.AddBook(this);
        }

        public void AddRental(String rentee) {
            AssertUtils.IsTrue(!string.IsNullOrEmpty(rentee));
            AssertUtils.IsTrue(rentals.LastOrDefault() == null || rentals.LastOrDefault().ReturnOfRental == default(DateTime), "Can't rent the book, it's currently rented.");
            rentals.Add(new Rental(this, rentee));
        }

        public IEnumerable<Rental> Rentals {
            get { return rentals; }
        }

        public void ReturnedFromRental() {
            AssertUtils.IsTrue(rentals.LastOrDefault() !=null && rentals.LastOrDefault().ReturnOfRental != default(DateTime), "Book isn't currently rented.");
            rentals.Last().ReturnOfRental = DateTime.UtcNow;
        }
    }
}

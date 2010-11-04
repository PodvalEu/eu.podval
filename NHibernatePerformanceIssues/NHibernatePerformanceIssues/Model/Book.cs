using System;
using System.Collections.Generic;
using Spring.Util;
using System.Linq;

namespace eu.podval.NHibernatePerformanceIssues.Model {
    public class Book {
        public String Name { get; set; }
        public String Author { get; set; }
        public int Pages { get; set; }

        public String Isbn { get; private set; }
        public Library Library { get; private set; }

        private ICollection<Rental> rentals = new List<Rental>();

        public Book(Library library, string isbn) {
            AssertUtils.IsTrue(!string.IsNullOrEmpty(isbn));
            AssertUtils.IsTrue(library != null, "Library is mandatory for book construction.");

            Isbn = isbn;
            Library = library;
        }

        public void AddRental(String rentee) {
            AssertUtils.IsTrue(!string.IsNullOrEmpty(rentee));
            AssertUtils.IsTrue(rentals.LastOrDefault() == null || rentals.LastOrDefault().ReturnOfRental == default(DateTime), "Can't rent the book, it's currently rented.");
            rentals.Add(new Rental(this, rentee));
        }

        public void ReturnedFromRental() {
            AssertUtils.IsTrue(rentals.LastOrDefault() !=null && rentals.LastOrDefault().ReturnOfRental != default(DateTime), "Book isn't currently rented.");
            rentals.Last().ReturnOfRental = DateTime.UtcNow;
        }
    }
}

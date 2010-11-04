using System;
using Spring.Util;

namespace eu.podval.NHibernatePerformanceIssues.Model {
    public class Rental {
        public String Rentee { get; private set; }
        public DateTime StartOfRental { get; private set; }
        public DateTime ReturnOfRental { get; set; }
        public Book Book { get; private set; }

        public Rental(Book book, string rentee) {
            AssertUtils.IsTrue(book != null);
            AssertUtils.IsTrue(rentee != null);

            Book = book;
            Rentee = rentee;
            StartOfRental = DateTime.UtcNow;
        }
    }
}

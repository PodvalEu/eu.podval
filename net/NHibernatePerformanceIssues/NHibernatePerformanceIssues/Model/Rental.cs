using System;
using Spring.Util;

namespace eu.podval.NHibernatePerformanceIssues.Model {
    public class Rental {
        private Guid id;
        public Guid Id {
            get { return id; }
            private set { id = value; }
        }

        public String Rentee { get; private set; }
        public DateTime StartOfRental { get; private set; }
        public DateTime ReturnOfRental { get; set; }
        public Book Book { get; private set; }

                /// <summary>
        /// Empty private constructor for persistence.
        /// </summary>
        private Rental() {
        }

        /// <summary>
        /// Default constructor assigning new <see cref="Guid"/> becuase entity live also un-persisted.
        /// </summary>
        public Rental(Book book, string rentee) {
            AssertUtils.IsTrue(book != null);
            AssertUtils.IsTrue(rentee != null);

            Id = new Guid();
            Book = book;
            Rentee = rentee;
            StartOfRental = DateTime.UtcNow;
        }
    }
}

using eu.podval.NHibernatePerformanceIssues.Model;
using FluentNHibernate.Mapping;

namespace eu.podval.NHibernatePerformanceIssues.Mapping {
    public class BookMapping : ClassMap<Book> {
        public BookMapping() {
            Id(b => b.Id).Column("Id").GeneratedBy.Assigned().Access.ReadOnlyPropertyThroughCamelCaseField();
            Map(b => b.Isbn);
            Map(b => b.Name);
            Map(b => b.Pages);
            References(b => b.Library).Column("LIBRARY_ID").LazyLoad().Index("IDX_BOOK_LIBRARY_ID").Cascade.None();

            HasMany(b => b.Rentals).
                Access.CamelCaseField().
                AsBag().
                Inverse().
                KeyColumn("BOOK_ID").
                ForeignKeyConstraintName("FK_RENTAL_BOOK_ID").
                ForeignKeyCascadeOnDelete().
                LazyLoad().
                OrderBy("StartOfRental").
                Cascade.AllDeleteOrphan();

            Not.LazyLoad();
        }
    }
}

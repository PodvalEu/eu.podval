using eu.podval.NHibernatePerformanceIssues.Model;
using FluentNHibernate.Mapping;

namespace eu.podval.NHibernatePerformanceIssues.Mapping {
    public class RentalMapping: ClassMap<Rental> {
        public RentalMapping() {
            Id(r => r.Id).Column("Id").GeneratedBy.Assigned().Access.ReadOnlyPropertyThroughCamelCaseField();
            Map(r => r.Rentee);
            Map(r => r.StartOfRental).CustomType("Ticks");
            Map(r => r.ReturnOfRental).CustomType("Ticks");
            References(r => r.Book).Column("BOOK_ID").LazyLoad().Index("IDX_RENTAL_BOOK_ID").Cascade.None();
            Not.LazyLoad();
        }
    }
}

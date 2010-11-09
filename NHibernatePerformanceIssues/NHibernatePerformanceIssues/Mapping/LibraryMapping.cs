using eu.podval.NHibernatePerformanceIssues.Model;
using FluentNHibernate.Mapping;

namespace eu.podval.NHibernatePerformanceIssues.Mapping {
    public class LibraryMapping: ClassMap<Library> {
        public LibraryMapping() {
            Id(l => l.Id).Column("Id").GeneratedBy.Assigned().Access.ReadOnlyPropertyThroughCamelCaseField();
            Map(l => l.Address);
            Map(l => l.Director);
            Map(l => l.Name);

            HasMany(l => l.Books).
                Access.CamelCaseField().
                AsBag().
                Inverse().
                KeyColumn("LIBRARY_ID").
                ForeignKeyConstraintName("FK_BOOK_LIBRARY_ID").
                ForeignKeyCascadeOnDelete().
                LazyLoad().
                Cascade.AllDeleteOrphan();

            Not.LazyLoad();
        }
    }
}

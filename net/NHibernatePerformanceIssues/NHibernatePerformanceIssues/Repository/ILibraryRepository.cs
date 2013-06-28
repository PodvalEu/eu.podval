using eu.podval.NHibernatePerformanceIssues.Model;

namespace eu.podval.NHibernatePerformanceIssues.Repository {
    public interface ILibraryRepository {
        void Add(Library library);
        void Delete(Library library);
    }
}
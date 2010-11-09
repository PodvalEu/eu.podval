using Common.Logging;
using eu.podval.NHibernatePerformanceIssues.Model;
using eu.podval.NHibernatePerformanceIssues.Repository;
using NHibernate;
using Spring.Objects.Factory.Attributes;
using Spring.Transaction;
using Spring.Transaction.Interceptor;

namespace eu.podval.NHibernatePerformanceIssues.NHibernateRepositoryImpl {
    public class LibraryRepository : ILibraryRepository {
        private static readonly ILog Log = LogManager.GetLogger(typeof(LibraryRepository));

        [Required]
        public ISessionFactory SessionFactory { private get; set; }

        [Transaction(TransactionPropagation.Mandatory)]
        public void Add(Library library) {
            Log.Debug("Adding library to repository.");
            SessionFactory.GetCurrentSession().Save(library);
            Log.Debug("Adding library to repository done.");
        }

        [Transaction(TransactionPropagation.Mandatory)]
        public void Delete(Library library) {
            Log.Debug("Removing library to repository.");
            SessionFactory.GetCurrentSession().Delete(library);
            Log.Debug("Removing library to repository done.");
        }
    }
}

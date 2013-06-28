using System;
using eu.podval.NHibernatePerformanceIssues.Model;
using eu.podval.NHibernatePerformanceIssues.Repository;
using NUnit.Framework;
using Spring.Objects.Factory.Attributes;
using Spring.Testing.NUnit;

namespace NHibernatePerformanceIssuesTests.Repository {

    [TestFixture]
    public class LibraryRepositoryTests : AbstractTransactionalSpringContextTests {
        protected override string[] ConfigLocations {
            get { return new[] { "assembly://NHibernatePerformanceIssues/eu.podval.NHibernatePerformanceIssues.Config/context.xml" }; }
        }

        private const string LibraryName = "My Library Name";
        private const string LibraryAddress = "White Street 156/a, London, UK";
        private const string LibraryDirector = "Charles Kingston";

        [Required]
        public ILibraryRepository LibraryRepository { private get; set; }

        [Test]
        public void TestAdd() {
            Library library = CreateLibrary();
            CreateBook(library);
            LibraryRepository.Add(library);
            SetComplete();
        }

        private static Library CreateLibrary() {
            return new Library(LibraryName) { Address = LibraryAddress, Director = LibraryDirector };
        }

        public static Book CreateBook(Library library) {
            Random random = new Random();
            return new Book(library, "80-200-0980-9") {Author = "Any author", Name = "Any name", Pages = random.Next()};
        }
    }
}

using System.Reflection;
using FluentNHibernate.Cfg;
using NHibernate.Cfg;
using NHibernate.Tool.hbm2ddl;
using Spring.Data.NHibernate;

namespace eu.podval.NHibernatePerformanceIssues.SessionFactory {
    public class FluentNHibernateSessionFactory : LocalSessionFactoryObject {

        private string[] fluentNhibernateMappingAssemblies;
        public string[] FluentNhibernateMappingAssemblies {
            get { return fluentNhibernateMappingAssemblies; }
            set { fluentNhibernateMappingAssemblies = value; }
        }

        protected override void PostProcessConfiguration(Configuration config) {
            base.PostProcessConfiguration(config);
            Fluently.Configure(config).
                ExposeConfiguration(cfg => new SchemaExport(cfg).SetOutputFile("create.sql").Create(true, true)).
                Mappings(m => {
                foreach (string assemblyName in fluentNhibernateMappingAssemblies) {
                    m.HbmMappings.AddFromAssembly(Assembly.Load(assemblyName));
                    m.FluentMappings.AddFromAssembly(Assembly.Load(assemblyName)).ExportTo(".\\");
                }
            }).
            BuildConfiguration();
        }

    }
}

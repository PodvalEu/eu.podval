// Type: Spring.Util.AssertUtils
// Assembly: Spring.Core, Version=0.0.0.20608, Culture=neutral, PublicKeyToken=65e474d141e25e07
// Assembly location: C:\Data\podval.eu\lib\spring.net\Spring.Core.dll

using System;
using System.Collections;
using System.Reflection;

namespace Spring.Util {
    public sealed class AssertUtils {
        public static void Understands(object target, string targetName, MethodBase method);
        public static void Understands(object target, string targetName, Type requiredType);
        public static void ArgumentNotNull(object argument, string name);
        public static void ArgumentNotNull(object argument, string name, string message);
        public static void ArgumentHasText(string argument, string name);
        public static void ArgumentHasText(string argument, string name, string message);
        public static void ArgumentHasLength(ICollection argument, string name);
        public static void ArgumentHasLength(ICollection argument, string name, string message);
        public static void ArgumentHasElements(ICollection argument, string name);
        public static void AssertArgumentType(object argument, string argumentName, Type requiredType, string message);
        public static void IsTrue(bool expression, string message);
        public static void IsTrue(bool expression);
        public static void State(bool expression, string message);
    }
}

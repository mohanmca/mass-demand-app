
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/mohan/git/new-app/conf/routes
// @DATE:Sun Jun 14 12:11:10 SGT 2015


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}

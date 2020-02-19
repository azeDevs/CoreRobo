package bot


import robo.Kit
import robo.models.Param
import robo.utils.prLn

class TestKit : Kit {

    constructor(title: String) : super(title)

    constructor(title: String, query: String) : super(title) {
//        setQuery(query)
    }

    constructor(title: String, query: String, vararg subKits: Kit) : super(title) {
//        setQuery(query)
//        installKits(*subKits)
    }

    override fun onQuery(params: List<Param>) {
        super.onQuery(params)
        params.stream().forEach { param -> prLn(param.toString()) }
    }
}
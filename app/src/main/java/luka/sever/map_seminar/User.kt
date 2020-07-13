package luka.sever.map_seminar

class User  {
    var id : Int = 0
    var name : String = ""
    var password : String = ""

    constructor(name:String, password:String)
    {
        this.name = name
        this.password = password
    }
}
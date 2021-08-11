package master.clean.architecture_kodein.data.login

class LoginResponse(
    val userId: Int?,
    val fullName: String?,
    val Phone: String?,
    val token: String?,
    val expired: Boolean?
)
package master.clean.architecture_kodein.internal

import java.io.IOException


class NoConnectivityException : IOException()
class LocationPermissionNotGrantedException : Exception()
class DateNotFoundException : Exception()
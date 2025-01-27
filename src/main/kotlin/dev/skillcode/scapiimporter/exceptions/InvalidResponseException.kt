package dev.skillcode.scapiimporter.exceptions

class InvalidResponseException : RuntimeException {

    companion object {
        private const val DEFAULT_MESSAGE = "Invalid response received"
    }

    constructor(
        message: String
    ) : super("$DEFAULT_MESSAGE $message")

    constructor() : super(DEFAULT_MESSAGE)
}

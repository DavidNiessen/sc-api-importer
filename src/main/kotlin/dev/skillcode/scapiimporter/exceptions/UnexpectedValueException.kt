package dev.skillcode.scapiimporter.exceptions

class UnexpectedValueException : RuntimeException {

    companion object {
        private const val DEFAULT_MESSAGE = "Unepected value received"
    }

    constructor(
        message: String
    ) : super("$DEFAULT_MESSAGE: $message")


    constructor(
        actual: String,
        expected: String
    ) : super("$DEFAULT_MESSAGE: value=$actual expected=$expected")

    constructor() : super(DEFAULT_MESSAGE)
}

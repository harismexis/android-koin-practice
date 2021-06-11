package com.harismexis.koinpractice.parser

abstract class BaseFileParser {

    abstract fun getFileAsString(filePath: String): String

}
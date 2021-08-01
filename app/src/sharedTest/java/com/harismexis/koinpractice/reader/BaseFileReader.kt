package com.harismexis.koinpractice.reader

abstract class BaseFileReader {

    abstract fun getFileAsString(filePath: String): String

}
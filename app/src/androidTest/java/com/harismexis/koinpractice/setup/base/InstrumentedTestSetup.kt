package com.harismexis.koinpractice.setup.base

import com.harismexis.koinpractice.parser.MockHerosParser
import com.harismexis.koinpractice.setup.testutil.InstrumentedFileParser

open class InstrumentedTestSetup {
    protected val fileParser = InstrumentedFileParser()
    protected val herosParser = MockHerosParser(fileParser)
}
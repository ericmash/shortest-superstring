/*
 * Tests for BruteForceSuperstring
 */

import spock.lang.Specification

class BruteForceSuperstringTest extends Specification{

    def 'Sample Dataset Test Case'() {
        setup:
        SuperstringStrategy strategy = new BruteForceSuperstring()
        List<String> dnaStrings = ['ATTAGACCTG', 'CCTGCCGGAA', 'AGACCTGCCG', 'GCCGGAATAC']
        when:
        def result = strategy.findShortestSuperstring(dnaStrings)
        then:
        result == 'ATTAGACCTGCCGGAATAC'
    }
}

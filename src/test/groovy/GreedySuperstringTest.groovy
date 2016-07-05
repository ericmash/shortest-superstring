/*
 * Tests for GreedySuperstring
 */

import spock.lang.Specification

class GreedySuperstringTest extends Specification{

    def 'Sample Dataset Test Case from Rosalind'() {
        setup:
        List<String> dnaStrings = ['ATTAGACCTG', 'CCTGCCGGAA', 'AGACCTGCCG', 'GCCGGAATAC']
        when:
        def result = new GreedySuperstring().findShortestSuperstring(dnaStrings)
        then:
        assert result == 'ATTAGACCTGCCGGAATAC'
    }
}

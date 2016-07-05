/*
 * Tests for BruteForceSuperstring
 */

import spock.lang.Specification

class BruteForceSuperstringTest extends Specification{

    def 'Sample Dataset Test Case from Rosalind'() {
        setup:
        List<String> dnaStrings = ['ATTAGACCTG', 'CCTGCCGGAA', 'AGACCTGCCG', 'GCCGGAATAC']
        when:
        def result = new BruteForceSuperstring().findShortestSuperstring(dnaStrings)
        then:
        assert result == 'ATTAGACCTGCCGGAATAC'
    }
}

/*
 * Tests for GreedySuperstring
 */

import spock.lang.Specification
import utils.TestSampleUtil

class GreedyDFSTest extends Specification{

    def 'Sample Dataset Test Case from Rosalind'() {
        setup:
        List<String> dnaStrings = ['ATTAGACCTG', 'CCTGCCGGAA', 'AGACCTGCCG', 'GCCGGAATAC']
        when:
        def result = new GreedyDFS().findShortestSuperstring(dnaStrings)
        then:
        assert result == 'ATTAGACCTGCCGGAATAC'
    }

    def 'Test cases from Driver Group'() {
        setup:
        List<String> dnaStrings = TestSampleUtil.instance.getDNAStrings()
        int totalBasePairs = dnaStrings.inject(0) { length, dnaString -> length + dnaString.length() }
        int maxLength = totalBasePairs / 2
        when:
        def result = new GreedyDFS().findShortestSuperstring(dnaStrings)
        then:
        assert result.length() > 0 && result.length() < maxLength  // make sure there is a result and assumption is kept
        assert dnaStrings.every { // make sure the superstring contains all the original DNA strings
            result.contains(it)
        }
    }
}

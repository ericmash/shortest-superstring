/*
 * Tests for GreedySuperstring
 */

import spock.lang.Specification
import utils.TestSampleUtil

class GreedyDFSTest extends Specification{

    def 'Test Sample Dataset from Rosalind'() {
        setup:
        List<String> dnaStrings = ['ATTAGACCTG', 'CCTGCCGGAA', 'AGACCTGCCG', 'GCCGGAATAC']
        when:
        def result = new GreedyDFS().findShortestSuperstring(dnaStrings)
        then:
        assert result == 'ATTAGACCTGCCGGAATAC'
    }

    def 'Test Dataset with and without overlaps'() {
        setup:
        List<String> dnaStrings = ['AAAAAAAA', 'AAAAAAAA', 'AAAAAAAA', 'AAAAAAAA', 'TTTTTTTT']
        when:
        def result = new BruteForcePermutation().findShortestSuperstring(dnaStrings)
        then:
        assert result == 'AAAAAAAATTTTTTTT'
    }

    def 'Test cases from Driver Group'() {
        setup:
        List<String> dnaStrings = TestSampleUtil.instance.getDNAStrings()
        int totalBasePairs = dnaStrings.inject(0) { length, dnaString -> length + dnaString.length() }
        int maxLength = totalBasePairs / 2
        when:
        def result = new GreedyDFS().findShortestSuperstring(dnaStrings)
        then:
        assert result.length() > 0 && result.length() < maxLength  // make sure there is a result and length is within assumption
        assert dnaStrings.every { // make sure the superstring contains all the original DNA strings
            result.contains(it)
        }
    }
}

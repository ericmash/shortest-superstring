/*
 * This Spock specification was auto generated by running the Gradle 'init' task
 * by 'ericmash' at '03/07/16 8:13 PM' with Gradle 2.14
 *
 * @author ericmash, @date 03/07/16 8:13 PM
 */

import spock.lang.Specification

class LibraryTest extends Specification{
    def "someLibraryMethod returns true"() {
        setup:
        Library lib = new Library()
        when:
        def result = lib.someLibraryMethod()
        then:
        result == true
    }
}
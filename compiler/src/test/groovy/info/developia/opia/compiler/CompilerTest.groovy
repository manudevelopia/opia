package info.developia.opia.compiler

import spock.lang.Specification

class CompilerTest extends Specification {

    def "should compile the opia code"(){
        given:
        String filename = '/Users/m4nu/Development/github/opia/compiler/src/test/resources/HelloWord.opia'
        when:
        new Compiler().compile(filename)
        then:
        noExceptionThrown()
    }
}

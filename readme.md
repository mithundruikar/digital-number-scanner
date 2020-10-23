
#Running locally
Main class: `com.scb.blade.number.scanner.application.Main` in application module.<br>
Program Arguments:
path of the file to be tested. <br>
e.g. 
C:\Users\mithu\IdeaProjects\digital-number-scanner\application\src\main\resources\multipleChunks

or we can use just a classpath resource by giving classpath resource path <br>
e.g.
multipleChunks

Here multipleChunks is present in application/src/main/resources folder.



Design - <br>
At high Level, I have separated the problem into 3 parts. It assembled them in streaming topology.

Reader - which reads input from the file and emits representation of file inputs in some internal Model (number Buffers)
matchers - Contains grammar and representation-to-number mappings
Parser - It tries to match internal model (for given input text) to integer number

There are multiple modules. Shown below with dependency growing from top to bottom:   

     <modules>
        <module>reader-buffer</module>
        <module>number-matchers</module>
        <module>input-reader</module>
        <module>number-parser</module>
        <module>application</module>
    </modules>

In terms of streaming flow, application module starts the stream topology.
input-reader scans the file and emits NumberLineInput for each digit line.
number-parser listens on these emitted NumberLineInput and performs matching. It emits string numeric representation.
These strings are internally consumed by ConsoleOutputSink. (This class can be moved to application module) 


Important classes:
<br> 
portfolio server - <br> 
`com.scb.blade.reader.buffer.model.NumberLineInput` <br>
`com.scb.blade.parser.NumberParserTopology` <br>
`com.scb.blade.presentation.NumberPresentationRepository` <br>

`com.scb.blade.reader.file.NumberTextAssembler` <br>



Important tests:
<br>
`com.scb.blade.reader.file.StatefulCyclicTextAssemblerTest`
`com.scb.blade.parser.NumberParserTopologyIT`


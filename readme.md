## Summary
This is a digital number scanner. Input will be a path to a file containing LCD style numbers. Output will be a numbers matching the LCD representation.
e.g.
<pre>
    _  _     _  _  _  _  _ 
  | _| _||_||_ |_   ||_||_|
  ||_  _|  | _||_|  ||_| _|
</pre>
  
will output
123456789

There are rules on how many LCD digits will be on one line, how many characters per digit and how 2 number line will be separated. 
By default it will be 9 digit per line and each digit will be 3 characters wide. Blank line denotes number line separation. 

## Running locally
Main class: `com.scb.blade.number.scanner.application.Main` in application module.<br>
Program Arguments:
path of the file to be tested. This can be either a absolute path or a classpath resource <br>

e.g. if passing absolute path of the file <br>
C:\Users\mithu\IdeaProjects\digital-number-scanner\application\src\main\resources\multipleChunks <br>

e.g. if passing path of the file present in the codebase <br>
multipleChunks <br>
Here multipleChunks is present in application/src/main/resources folder.

## Automtated Testing inputs using Acceptance/IT test
End to end component test is available at `com.scb.blade.number.scanner.application.MainScannerTestIT` in application module. It has test cases to test given 3 file inputs.
New can be added and asserted with returned list of string value.

## Application configuration properties
Application module contains application.properties with supported application properties to control the rules of the parsing, scanning and matching.

## Design
At high Level, I have separated the problem into 3 parts. These are parts are joined in streaming topology to achieve an event driven application flow.

**input-reader -**<br> which reads input from the file and emits representation of file inputs in some internal Model (number Buffers) <br>
**number-matchers -**<br> Contains grammar and representation-to-number mappings<br>
**number-parser -**<br> It tries to match internal model (for given input text) to integer number<br>

For streaming implementation, RxJava library is used.

## Modules
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
number-parser listens on these emitted NumberLineInput and performs matching. It emits string formatted numeric representation.
These strings are later consumed by ConsoleOutputSink in application module. 


## Important classes:
<br>

**portfolio server -** <br> 
`com.scb.blade.reader.buffer.model.NumberLineInput` <br>
`com.scb.blade.parser.NumberParserTopology` <br>
`com.scb.blade.reader.file.NumberTextAssemblerTopology` <br>
`com.scb.blade.presentation.NumberPresentationRepository` <br>



**Important tests:**
<br>
&emsp; &emsp;End-to-End IT - <br>
 &emsp; &emsp;&emsp; &emsp;`com.scb.blade.number.scanner.application.MainScannerTestIT` <br>
&emsp; &emsp;Reader module - <br>
&emsp; &emsp;&emsp; &emsp;`com.scb.blade.reader.file.NumberTextAssemblerTopologyTest` <br> 
&emsp; &emsp;&emsp; &emsp;`com.scb.blade.reader.file.StatefulCyclicTextAssemblerTest` <br>
&emsp; &emsp;Parser module - <br>
&emsp; &emsp;&emsp; &emsp;`com.scb.blade.parser.NumberParserTopologyIT`



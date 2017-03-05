# KiCad BOM #

This java app will transform a XML BOM output file from KiCad into a CSV
formatted to be easly used for [Octopart BOM tool](https://octopart.com/bom-tool).
This tool makes the assumption that you put the manufacture part numbers into
your components under the field "MPN".

#### Usage ####

* Add a 'MPN' field to each component in your schematic
* Create the BOM xml file from KiCad (Tools --> Generate Bill Of Materials)
* run this app: `java -jar build/libs/kicadbom-proguard.jar -i <path/to/your/XMLfile>`
* open https://octopart.com/bom-tool and drag-drop the resulting CSV file

# WordCombiner

## Description

WordCombiner is a program designed to streamline document creation by combining multiple word files using a specified template. The inspiration for developing this program stems from the need to automate document generation for a court in Moscow. While there is an existing version, it fails to meet all requirements. Hence, a new version is under construction, leveraging knowledge acquired at TUM (Technische Universität München).

## Functionality

The program takes four input files: a complaint, followed by an appeal definition, a template, and a configuration file. Following configuration, the program extracts specific text sections from the first two documents and inserts them into the template at designated locations. Users create configurations, where the first word signifies the position in the template. Depending on the command type, a one-syllable command or a two-syllable command is employed.

### One-syllable command

The one-syllable command involves two options: copying a sentence or copying a paragraph. It may also include multiple operations such as "AND," "OR," considering case sensitivity of a word combination, and incorporating it into a found piece of text or not. By default, all words and word combinations are case-sensitive and included in the text. For example:

```plaintext
"AAA !BBB*OR*(CCC)*OR*DDD*OR*(EEEEEE)! 13"
```

This command means that at the location of AAA in the template, the thirteenth sentence from the text should be inserted, which includes either BBB or case-insensitive CCC "()" or DDD or case-insensitive EEEEEE "()".

### Two-syllable command

The two-syllable command involves specifying a portion of text between two points to be inserted at the location indicated in the template. The points are defined by two words, and the enclosed text should replace the specified placeholder in the template. Both words are case-insensitive and should not be included in the copied phrase. For example:

```plaintext
"AAA @/(BBB)/@ @(/CCC/)@ LAST"
```

This command implies that the text between BBB and CCC should replace AAA in the template, with BBB and CCC being case-insensitive "()".

## Usage

Further explanations and rules for creating configurations will be provided once the program is complete. Currently, file reading and command definition functionalities are operational, with the pending task of inserting text into the template and locating the correct text segments in the files.

For additional examples and syntax, please refer to the `Tests.class` file.

## Getting Started

To get started, clone the repository and follow the instructions in the Tests.class file for sample configurations and usage scenarios.

## Issues and Contributions

If you encounter any issues or have suggestions for improvements, feel free to open an issue or contribute to me

**Note:** The syntax in the ReadMe may not always be accurate. Refer to the `Tests.class` file for numerous examples.

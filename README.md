# Taxpayer Registration System (Java Swing GUI)

An advanced Desktop Graphical User Interface (GUI) application designed to capture detailed taxpayer information. Built using Java Swing, this form supports comprehensive inputs, including multi-language fields, citizenship criteria, regional routing, and source of income selection.

## 🚀 Key Features
- **Trilingual Support:** Specialized input fields capturing names and addresses in English, Sinhala, and Tamil.
- **Rich UI Elements:** Implements a variety of Swing components including `JComboBox`, `JRadioButton`, `JCheckBox`, and `JTextField` to handle complex states.
- **Dynamic Layout Architecture:** Combines `BoxLayout`, `GridLayout`, and `FlowLayout` wrapped inside a `JScrollPane` to ensure a responsive, clean, and adaptive user interface across different screen dimensions.
- **Native OS Look & Feel:** Integrated with `UIManager` to automatically adapt to the native system's look and feel for a modern desktop application experience.
- **Form State Management:** Features full validation check alerts and a robust global form reset function.

## 🛠️ Tech Stack & Concepts Used
- **Language:** Core Java (JDK 8+)
- **GUI Framework:** Java Swing (`JFrame`, `JPanel`, `JScrollPane`, `ButtonGroup`, `JOptionPane`)
- **Advanced Layouts:** Managed compound nested panels utilizing different layout constraints to structure 6+ distinct data validation sections.

## 📂 Project Structure
- `TaxPayer.java` - Contains the absolute structural components, interactive event action handlers, user confirmation prompts, and GUI logic.

# Algorithm Master 🚀

Welcome to **Algorithm Master** - a world-class, interactive Java console application featuring comprehensive implementations of searching and sorting algorithms! This project demonstrates professional coding standards, beautiful user interfaces, and educational visualizations.

## ✨ Features

### 🎯 **Professional User Experience**
- Beautiful ASCII welcome screen with interactive menu system
- Advanced error handling and robust input validation
- Step-by-step algorithm visualization with pause controls
- Comprehensive performance metrics and complexity analysis
- Educational explanations for each algorithm

### 🔍 **Searching Algorithms**
1. **Linear Search** - O(n) sequential search
2. **Binary Search** - O(log n) divide-and-conquer (recursive & iterative)
3. **Jump Search** - O(√n) block-based search
4. **Interpolation Search** - O(log log n) for uniformly distributed data
5. **Exponential Search** - O(log n) unbounded binary search

### 🗂️ **Sorting Algorithms**
10. **Bubble Sort** - O(n²) with optimization options
11. **Selection Sort** - O(n²) minimum-finding approach
12. **Insertion Sort** - O(n²) incremental sorting
13. **Merge Sort** - O(n log n) stable divide-and-conquer
14. **Quick Sort** - O(n log n) average with multiple pivot strategies
15. **Heap Sort** - O(n log n) heap-based sorting
16. **Radix Sort** - O(d×n) non-comparative digit sorting
17. **Shell Sort** - O(n^1.25) gap-based insertion sort with multiple sequences

## 🛠️ **Technical Excellence**

### **Code Organization**
```
src/
├── core/           # Main application and menu system
├── searching/      # All search algorithm implementations
├── sorting/        # All sorting algorithm implementations
└── utils/          # Input validation and visualization utilities
```

### **Advanced Features**
- **Multiple Implementation Variants**: Recursive vs iterative, different pivot strategies
- **Gap Sequence Options**: Shell's, Knuth's, and Hibbard's sequences for Shell Sort
- **Comprehensive Metrics**: Comparisons, swaps, shifts, execution time
- **Educational Visualizations**: Step-by-step algorithm execution with pause controls
- **Robust Error Handling**: Graceful handling of edge cases and invalid inputs

## 🚀 **Getting Started**

### **Quick Start**
```cmd
# Navigate to source directory
cd src

# Compile the application
javac core/Main.java

# Run Algorithm Master
java core.Main
```

### **Using VS Code**
1. Open the workspace in VS Code
2. Use `Ctrl+Shift+P` → "Tasks: Run Task" → "Build and Run Algorithm Master"
3. Or use the terminal commands above

## 📊 **Algorithm Complexity Summary**

| Algorithm | Best Case | Average Case | Worst Case | Space | Stable |
|-----------|-----------|--------------|------------|-------|--------|
| Linear Search | O(1) | O(n) | O(n) | O(1) | ✅ |
| Binary Search | O(1) | O(log n) | O(log n) | O(1) | ❌ |
| Jump Search | O(1) | O(√n) | O(√n) | O(1) | ❌ |
| Interpolation | O(1) | O(log log n) | O(n) | O(1) | ❌ |
| Exponential | O(1) | O(log n) | O(log n) | O(1) | ❌ |
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | ✅ |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) | ❌ |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | ✅ |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | ✅ |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | ❌ |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | ❌ |
| Radix Sort | O(n) | O(d×n) | O(d×n) | O(n+k) | ✅ |
| Shell Sort | O(n) | O(n^1.25) | O(n²) | O(1) | ❌ |

## 🎓 **Educational Value**

This project serves as an excellent learning resource for:
- **Algorithm Analysis**: Understanding time and space complexity
- **Implementation Techniques**: Multiple approaches to the same problem
- **Code Quality**: Professional Java development practices
- **Data Structures**: Heaps, arrays, and algorithmic thinking
- **Optimization**: Various optimization strategies and trade-offs

## 🏗️ **Architecture Highlights**

- **Modular Design**: Clean separation of concerns across packages
- **Extensible Framework**: Easy to add new algorithms
- **Professional Error Handling**: Comprehensive input validation
- **Interactive Visualizations**: Educational step-by-step execution
- **Performance Monitoring**: Built-in timing and counting metrics

## 🤝 **Contributing**

Contributions are welcome! Please ensure:
- Follow existing code style and documentation standards
- Add comprehensive comments and educational explanations
- Include visualization support for new algorithms
- Maintain the high-quality user experience standards

## 📝 **License**

This project is created for educational purposes and demonstrates professional Java development practices.

---

**Algorithm Master** - Where algorithms come to life! 🎯✨

package com.amd.aparapi.internal.jni;

import java.util.List;

import com.amd.aparapi.Kernel;
import com.amd.aparapi.ProfileInfo;
import com.amd.aparapi.Range;
import com.amd.aparapi.annotation.Experimental;
import com.amd.aparapi.device.OpenCLDevice;
import com.amd.aparapi.internal.annotation.DocMe;
import com.amd.aparapi.internal.annotation.UsedByJNICode;

/**
 * This class is intended to be used as a 'proxy' or 'facade' object for Java code to interact with JNI
 */
public abstract class KernelRunnerJNI {

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> represents a <code>boolean</code> type (array or primitive).
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_BOOLEAN = 1 << 0;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> represents a <code>byte</code> type (array or primitive).
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_BYTE = 1 << 1;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> represents a <code>float</code> type (array or primitive).
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_FLOAT = 1 << 2;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> represents a <code>int</code> type (array or primitive).
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_INT = 1 << 3;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> represents a <code>double</code> type (array or primitive).
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_DOUBLE = 1 << 4;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> represents a <code>long</code> type (array or primitive).
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_LONG = 1 << 5;

   /**
    * TODO:
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_SHORT = 1 << 6;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> represents an array.<br/>
    * So <code>ARG_ARRAY|ARG_INT</code> tells us this arg is an array of <code>int</code>.
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_ARRAY = 1 << 7;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> represents a primitive (non array).<br/>
    * So <code>ARG_PRIMITIVE|ARG_INT</code> tells us this arg is a primitive <code>int</code>.
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_PRIMITIVE = 1 << 8;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> is read by the Kernel (note from the Kernel's point of view).<br/>
    * So <code>ARG_ARRAY|ARG_INT|ARG_READ</code> tells us this arg is an array of int's that are read by the kernel.
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_READ = 1 << 9;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> is mutated by the Kernel (note from the Kernel's point of view).<br/>
    * So <code>ARG_ARRAY|ARG_INT|ARG_WRITE</code> tells us this arg is an array of int's that we expect the kernel to mutate.
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_WRITE = 1 << 10;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> resides in local memory in the generated OpenCL code.<br/>
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * @see com.amd.aparapi.annotation.Experimental
    * 
    * @author gfrost
    */
   @Experimental @UsedByJNICode protected static final int ARG_LOCAL = 1 << 11;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> resides in global memory in the generated OpenCL code.<br/>
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * @see com.amd.aparapi.annotation.Experimental
    * 
    * @author gfrost
    */
   @Experimental @UsedByJNICode protected static final int ARG_GLOBAL = 1 << 12;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> resides in constant memory in the generated OpenCL code.<br/>
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * @see com.amd.aparapi.annotation.Experimental
    * 
    * @author gfrost
    */
   @Experimental @UsedByJNICode protected static final int ARG_CONSTANT = 1 << 13;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> has it's length reference, in which case a synthetic arg is passed (name mangled) to the OpenCL kernel.<br/>
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_ARRAYLENGTH = 1 << 14;

   /**
    * TODO:
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_APARAPI_BUFFER = 1 << 15;

   /**
    * This 'bit' indicates that the arg has been explicitly marked for reading
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_EXPLICIT = 1 << 16;

   /**
    * This 'bit' indicates that the arg has been explicitly marked for writing
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_EXPLICIT_WRITE = 1 << 17;

   /**
    * TODO:
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_OBJ_ARRAY_STRUCT = 1 << 18;


   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> represents a <code>char</code> type (array or primitive).
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author rlamothe
    */
   @UsedByJNICode protected static final int ARG_CHAR = 1 << 21;

   /**
    * This 'bit' indicates that a particular <code>KernelArg</code> represents a <code>static</code> field (array or primitive).
    * 
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int ARG_STATIC = 1 << 22;

   /**
    * This 'bit' indicates that we wish to enable profiling from the JNI code.
    * 
    * @see com.amd.aparapi.annotations.UsedByJNICode
    * 
    * @author gfrost
    */
   //@UsedByJNICode protected static final int JNI_FLAG_ENABLE_PROFILING = 1 << 0;

   /**
    * This 'bit' indicates that we wish to store profiling information in a CSV file from JNI code.
    * 
    * @see com.amd.aparapi.annotations.UsedByJNICode
    * 
    * @author gfrost
    */
   // @UsedByJNICode protected static final int JNI_FLAG_ENABLE_PROFILING_CSV = 1 << 1;

   /**
    * This 'bit' indicates that we want to execute on the GPU.
    * 
    * Be careful changing final constants starting with JNI.<br/>
    * 
    * @see com.amd.aparapi.internal.annotation.UsedByJNICode
    * 
    * @author gfrost
    */
   @UsedByJNICode protected static final int JNI_FLAG_USE_GPU = 1 << 2;

   /**
    * Returns an amount of {@link ProfileInfo} for a given kernel.
    * @param kernelContextHandle kernel handle to get the profile info for
    * @return profile info
    */
   protected native synchronized List<ProfileInfo> getProfileInfoJNI(long kernelContextHandle);

   /**
    * Init the JNI environment for a new KernelRunner.
    * Synchronized to avoid race in clGetPlatformIDs() in OpenCL lib problem should fixed in some future OpenCL version.
    *
    * @param _device device the KernelRunner is bound to
    * @param _flags currently only {@link KernelRunnerJNI#JNI_FLAG_USE_GPU}.
    * @return identifier for the native KernelRunner context.
    */
   protected synchronized native long initKernelRunnerJNI(OpenCLDevice _device, int _flags);

   /**
    * Init the environment for the given {@link Kernel} on JNI side.
    *
    * @param _kernelRunnerHandle handle for the current kernel runner on JNI side
    * @param _kernel kernel to prepare for
    * @return handle for the kernel on JNI side
    */
   protected native long initKernelJNI(long _kernelRunnerHandle, Kernel _kernel);

   /**
    * Update the environment for the given {@link Kernel} on JNI side. This is required as on JNI side the
    * KernelRunnerContext class will store a reference to the kernel object to extract arguments. When running
    * kernels multiple times with different arguments we have to replace this reference to use valid values during
    * execution.
    *
    * @param _kernelHandle relates to the kernel context on JNI side
    * @param _kernel kernel object to execute
    * @return 1 if the kernel could not be found, else 0
    */
   protected native long updateKernelJNI(long _kernelHandle, Kernel _kernel);

   /**
    * Build the given source code for the given kernel runner and kernel handle.
    *
    * @param _kernelRunnerHandle relates to the runner context on JNI side
    * @param _kernelHandle relates to the kernel context on JNI side
    * @param _source source code to compile
    * @return kernelHandle or 0 (if an error occurred)
    */
   protected native long buildProgramJNI(long _kernelRunnerHandle, long _kernelHandle, String _source);

   /**
    * Set the given array of {@link KernelArgJNI} objects on the kernel specified by _kernelHandle. The method
    * must be called once, when the argument list of the kernel was determined the first time.
    *
    * @param _kernelRunnerHandle relates to the runner context on JNI side
    * @param _kernelHandle relates to the kernel context on JNI side
    * @param _args array of arguments to set
    * @param _argc number of arguments
    * @return OpenCL status code
    */
   protected native int setArgsJNI(long _kernelRunnerHandle, long _kernelHandle, KernelArgJNI[] _args, int _argc);

   /**
    * Run the kernel.
    *
    * @param _kernelRunnerHandle relates to the runner context on JNI side
    * @param _kernelHandle relates to the kernel context on JNI side
    * @param _range range to use for running (device is ignored!)
    * @param _needSync specifies whether kernel arguments have to be updated before execution
    * @param _passes times to run the kernel
    * @return OpenCL status code
    */
   protected native int runKernelJNI(long _kernelRunnerHandle, long _kernelHandle, Range _range, boolean _needSync, int _passes);

   /**
    * Dispose a KernelRunner specified by the given handle.
    *
    * @param _kernelRunnerHandle relates to the runner context on JNI side
    * @return OpenCL status code
    */
   protected native int disposeKernelRunnerJNI(long _kernelRunnerHandle);

   /**
    * Copy an element back from the GPU.
    *
    * @param _kernelRunnerHandle relates to the runner context on JNI side
    * @param _array array reference to copy back
    * @return OpenCL status code
    */
   protected native int getJNI(long _kernelRunnerHandle, Object _array);

   /**
    * Get extensions for the given runner handle.
    * @param _kernelRunnerHandle relates to the runner context on JNI side
    * @return String of extensions
    */
   protected native String getExtensionsJNI(long _kernelRunnerHandle);
}

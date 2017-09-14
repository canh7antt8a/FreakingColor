package com.hungle.freakingcolor;

import org.robovm.rt.bro.Bro;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.ptr.BytePtr;
import org.robovm.rt.bro.ptr.IntPtr;

@Library("SystemConfiguration")
public class Connect {

  static {
    Bro.bind();
  }

  public static class Flags {
    public static final int TransientConnection = 1<<0;
    public static final int Reachable = 1<<1;
    public static final int ConnectionRequired = 1<<2;
    public static final int ConnectionOnTraffic = 1<<3;
    public static final int InterventionRequired = 1<<4;
    public static final int ConnectionOnDemand = 1<<5;
    public static final int IsLocalAddress = 1<<16;
    public static final int IsDirect = 1<<17;
    public static final int IsWWAN = 1<<18;
    public static final int ConnectionAutomatic = ConnectionOnTraffic;
  }

  @Bridge public static native @Pointer long SCNetworkReachabilityCreateWithName(@Pointer long allocator, BytePtr nodename);
  @Bridge public static native boolean SCNetworkReachabilityGetFlags(@Pointer long target, IntPtr flags);

}

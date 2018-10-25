package kr.co.pjm.diving.web.common.enumeration;

public enum PagingEnum {
  DEFAULT(10, 10), DIVE_LOG(20, 10);

  private int recordSize;
  private int blockSize;

  private PagingEnum(int recordSize, int blockSize) {
    this.blockSize = blockSize;
    this.recordSize = recordSize;
  }

  public int getRecordSize() {
    return this.recordSize;
  }

  public int getBlockSize() {
    return this.blockSize;
  }
}

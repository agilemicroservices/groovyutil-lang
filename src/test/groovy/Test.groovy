final def TEST_DIR = "/tmp/test1/"
MakeDirectory(TEST_DIR)
new File("${TEST_DIR}/file.txt").createNewFile()
ZipDirectory(TEST_DIR)
DeleteDirectory(TEST_DIR)

.class public Lcom/zpj/hotfix/patch_dev/super_method/Test_Fix;
.super Lcom/zpj/hotfix/patch_dev/super_method/Base_Fix;
.source "Test.java"

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field private final synthetic mBugObj:Lcom/zpj/hotfix/patch_dev/super_method/Test;

.method public constructor <init>(Lcom/zpj/hotfix/patch_dev/super_method/Test;)V
    .registers 2
    .param p1, "test"    # Lcom/zpj/hotfix/patch_dev/super_method/Test;

    .prologue
    invoke-direct {p0, p1}, Lcom/zpj/hotfix/patch_dev/super_method/Base_Fix;-><init>(Lcom/zpj/hotfix/patch_dev/super_method/Base;)V

    iput-object p1, p0, Lcom/zpj/hotfix/patch_dev/super_method/Test_Fix;->mBugObj:Lcom/zpj/hotfix/patch_dev/super_method/Test;

    return-void
.end method

# virtual methods
.method public test()V
    .registers 3
    .annotation runtime Lcom/zpj/hotfix/annotation/Fix;
        method = "test"
        clazz = "com.zpj.hotfix.patch_dev.super_method.Test"
    .end annotation

    .line 12
    const-string v0, "Test"

    const-string v1, "test fixed!"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 13
    invoke-direct {p0}, Lcom/zpj/hotfix/patch_dev/super_method/Test_Fix;->_super_test()V
    .line 14
    return-void
.end method

.method public test(ILjava/lang/Integer;IDILjava/lang/Object;IIIIJIJLjava/lang/Long;)V
    .registers 38
    .param p1, "a"    # I
    .param p2, "b"    # Ljava/lang/Integer;
    .param p3, "c"    # I
    .param p4, "d"    # D
    .param p6, "e"    # I
    .param p7, "f"    # Ljava/lang/Object;
    .param p8, "g"    # I
    .param p9, "h"    # I
    .param p10, "i"    # I
    .param p11, "j"    # I
    .param p12, "k"    # J
    .param p14, "l"    # I
    .param p15, "m"    # J
    .param p17, "n"    # Ljava/lang/Long;
    .annotation runtime Lcom/zpj/hotfix/annotation/Fix;
        method = "test"
        clazz = "com.zpj.hotfix.patch_dev.super_method.Test"
    .end annotation

    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/zpj/hotfix/patch_dev/super_method/Test_Fix;->mBugObj:Lcom/zpj/hotfix/patch_dev/super_method/Test;

    .local v0, "_thisBugObj":Lcom/zpj/hotfix/patch_dev/super_method/Test;

    move-object/from16 v1, v0

    move-object/from16 v3, p2

    move/from16 v4, p3

    move-wide/from16 v5, p4

    move/from16 v7, p6

    move/from16 v9, p8

    move/from16 v10, p9

    move/from16 v11, p10

    move/from16 v12, p11

    move-wide/from16 v13, p12

    move/from16 v15, p14

    move-wide/from16 v16, p15

    .line 18
    const-wide/16 v18, 0x64

    invoke-static/range {v18 .. v19}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v18

    const/4 v2, 0x0

    const/4 v8, 0x0

    move-object/from16 v1, p0

    invoke-direct/range {v1 .. v18}, Lcom/zpj/hotfix/patch_dev/super_method/Test_Fix;->_super_test(ILjava/lang/Integer;IDILjava/lang/Object;IIIIJIJLjava/lang/Long;)V
    .line 19
    return-void
.end method


.method private _super_test(ILjava/lang/Integer;IDILjava/lang/Object;IIIIJIJLjava/lang/Long;)V

.registers 22

.param p1, "arg0"   # I

.param p2, "arg1"   # Ljava/lang/Integer;

.param p3, "arg2"   # I

.param p4, "arg3"   # D

.param p6, "arg4"   # I

.param p7, "arg5"   # Ljava/lang/Object;

.param p8, "arg6"   # I

.param p9, "arg7"   # I

.param p10, "arg8"   # I

.param p11, "arg9"   # I

.param p12, "arg10"   # J

.param p14, "arg11"   # I

.param p15, "arg12"   # J

.param p17, "arg13"   # Ljava/lang/Long;

.annotation system Ldalvik/annotation/Throws;
	value = {
		Ljava/lang/Exception;
	}
.end annotation

.prologue

const/16 v1, 0xe

new-array v0, v1, [Ljava/lang/Object;

const/4 v1, 0x0

invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

move-result-object v2

aput-object v2, v0, v1

const/4 v1, 0x1

aput-object p2, v0, v1

const/4 v1, 0x2

invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

move-result-object v2

aput-object v2, v0, v1

const/4 v1, 0x3

invoke-static {p4, p5}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

move-result-object v2

aput-object v2, v0, v1

const/4 v1, 0x4

invoke-static {p6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

move-result-object v2

aput-object v2, v0, v1

const/4 v1, 0x5

aput-object p7, v0, v1

const/4 v1, 0x6

invoke-static {p8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

move-result-object v2

aput-object v2, v0, v1

const/4 v1, 0x7

invoke-static {p9}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

move-result-object v2

aput-object v2, v0, v1

const/16 v1, 0x8

invoke-static {p10}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

move-result-object v2

aput-object v2, v0, v1

const/16 v1, 0x9

invoke-static {p11}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

move-result-object v2

aput-object v2, v0, v1

const/16 v1, 0xa

invoke-static/range {p12 .. p13}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

move-result-object v2

aput-object v2, v0, v1

const/16 v1, 0xb

invoke-static/range {p14 .. p14}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

move-result-object v2

aput-object v2, v0, v1

const/16 v1, 0xc

invoke-static/range {p15 .. p16}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

move-result-object v2

aput-object v2, v0, v1

const/16 v1, 0xd

aput-object p17, v0, v1

.local v0, "arr":[Ljava/lang/Object;

iget-object v1, p0, Lcom/zpj/hotfix/patch_dev/super_method/Test_Fix;->mBugObj:Lcom/zpj/hotfix/patch_dev/super_method/Test;

const-string v2, "test"

const-string v3, "(ILjava/lang/Integer;IDILjava/lang/Object;IIIIJIJLjava/lang/Long;)V"

invoke-static {v1, v2, v3, v0}, Lcom/zpj/hotfix/utils/Reflect;->invokeSuperVoid(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V

return-void.end method

.method private _super_test()V

.registers 5

.annotation system Ldalvik/annotation/Throws;
	value = {
		Ljava/lang/Exception;
	}
.end annotation

.prologue

const/4 v1, 0x0

new-array v0, v1, [Ljava/lang/Object;

.local v0, "arr":[Ljava/lang/Object;

iget-object v1, p0, Lcom/zpj/hotfix/patch_dev/super_method/Test_Fix;->mBugObj:Lcom/zpj/hotfix/patch_dev/super_method/Test;

const-string v2, "test"

const-string v3, "()V"

invoke-static {v1, v2, v3, v0}, Lcom/zpj/hotfix/utils/Reflect;->invokeSuperVoid(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V

return-void.end method
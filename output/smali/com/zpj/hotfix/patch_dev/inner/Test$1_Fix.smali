.class Lcom/zpj/hotfix/patch_dev/inner/Test$1_Fix;
.super Ljava/lang/Object;
.source "Test.java"

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field private final synthetic mBugObj:Lcom/zpj/hotfix/patch_dev/inner/Test$1;

# direct methods
.method public constructor <init>(Lcom/zpj/hotfix/patch_dev/inner/Test$1;)V
    .registers 2
    .param p1, "mBugObj"

    .prologue
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/zpj/hotfix/patch_dev/inner/Test$1_Fix;->mBugObj:Lcom/zpj/hotfix/patch_dev/inner/Test$1;

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3
    .annotation runtime Lcom/zpj/hotfix/annotation/Fix;
        method = "run"
        clazz = "com.zpj.hotfix.patch_dev.inner.Test$1"
    .end annotation

    .line 15
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "test="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-direct {p0}, Lcom/zpj/hotfix/patch_dev/inner/Test$1_Fix;->_get_this$0()Lcom/zpj/hotfix/patch_dev/inner/Test;

    move-result-object v1


    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "Test"

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 16
    return-void
.end method


.method private _get_this$0()Lcom/zpj/hotfix/patch_dev/inner/Test;

.registers 3

.annotation system Ldalvik/annotation/Throws;
	value = {
		Ljava/lang/Exception;
	}
.end annotation

.prologue

iget-object v0, p0, Lcom/zpj/hotfix/patch_dev/inner/Test$1_Fix;->mBugObj:Lcom/zpj/hotfix/patch_dev/inner/Test$1;

const-string v1, "this$0"

invoke-static {v0, v1}, Lcom/zpj/hotfix/utils/Reflect;->getField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

move-result-object v0

check-cast v0, Lcom/zpj/hotfix/patch_dev/inner/Test;

return-object v0

.end method
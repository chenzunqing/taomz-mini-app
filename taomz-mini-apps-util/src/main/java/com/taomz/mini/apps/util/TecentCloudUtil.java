package com.taomz.mini.apps.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.live.v20180801.LiveClient;
import com.tencentcloudapi.live.v20180801.models.DayStreamPlayInfo;
import com.tencentcloudapi.live.v20180801.models.DescribeLiveForbidStreamListRequest;
import com.tencentcloudapi.live.v20180801.models.DescribeLiveForbidStreamListResponse;
import com.tencentcloudapi.live.v20180801.models.DescribeLiveStreamPushInfoListRequest;
import com.tencentcloudapi.live.v20180801.models.DescribeLiveStreamPushInfoListResponse;
import com.tencentcloudapi.live.v20180801.models.DescribeStreamDayPlayInfoListRequest;
import com.tencentcloudapi.live.v20180801.models.DescribeStreamDayPlayInfoListResponse;
import com.tencentcloudapi.live.v20180801.models.DescribeStreamPlayInfoListRequest;
import com.tencentcloudapi.live.v20180801.models.DescribeStreamPlayInfoListResponse;
import com.tencentcloudapi.live.v20180801.models.DropLiveStreamRequest;
import com.tencentcloudapi.live.v20180801.models.DropLiveStreamResponse;
import com.tencentcloudapi.live.v20180801.models.ForbidLiveStreamRequest;
import com.tencentcloudapi.live.v20180801.models.ForbidLiveStreamResponse;
import com.tencentcloudapi.live.v20180801.models.ForbidStreamInfo;
import com.tencentcloudapi.live.v20180801.models.PlayDataInfoByStream;
import com.tencentcloudapi.live.v20180801.models.PushDataInfo;
import com.tencentcloudapi.live.v20180801.models.ResumeLiveStreamRequest;
import com.tencentcloudapi.live.v20180801.models.ResumeLiveStreamResponse;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : TecentCloudUtil
 * @Package : com.taomz.live.portal.util
 * @Description: 云直播操作工具类
 * @date 2020/7/21 14:47
 **/
@Slf4j
public class TecentCloudUtil {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss'Z'");

    private static final SimpleDateFormat FORMAT1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat FORMAT2 = new SimpleDateFormat("yyyy-MM-dd");

    private static Credential credential = null;

    private static LiveClient liveClient = null;

    // 推流域名
    public static final String PUSH_DOMAIN = "";
    // 拉流域名
    public static final String LIVE_DOMAIN = "";
    // API回调地址
    public static final String API_ADDRESS = "live.tencentcloudapi.com";

    public static final String PUSH_DOMIAN_NAME = PUSH_DOMAIN + ".livepush.myqcloud.com";

    public static final String LIVE_DOMIAN_NAME = LIVE_DOMAIN + ".livecdn.liveplay.myqcloud.com";

    public static final String APP_NAME = "live";

    /**
     * 推流地址
     */
    public static final String PUSH_URL = "rtmp://" + PUSH_DOMAIN + "/";

    private static TecentCloudUtil tecentCloudUtil = new TecentCloudUtil();

    private String secretId;

    private String secretKey;

    private TecentCloudUtil setSecretInfo(String secretId, String secretKey) {
        tecentCloudUtil.setSecretInfo(secretId, secretKey);
        return tecentCloudUtil;
    }

    /**
     * 实例化认证对象
     *
     * @return
     */
    private synchronized Credential getCredential() {
        if (credential == null) {
            credential = new Credential(secretId, secretId);
        }
        return credential;
    }

    /**
     * 实例化认证对象
     *
     * @return
     */
    private LiveClient getLiveClient() {
        if (liveClient == null) {
            liveClient = new LiveClient(getCredential(), "");
        }
        return liveClient;
    }

    /**
     * 禁推 一个直播流 全参数
     * @param streamName
     *            直播流名字
     * @param appName
     *            应用名称
     * @param resumeTime
     *            禁推时长（单位分钟）
     * @return 请求 ID
     * @throws TencentCloudSDKException
     */
    public String forbidLiveStream(String streamName, String appName, Long resumeTime)
            throws TencentCloudSDKException {
        Long currentTime = System.currentTimeMillis() + resumeTime * 60L * 1000L;
        LiveClient client = getLiveClient();
        ForbidLiveStreamRequest req = new ForbidLiveStreamRequest();
        req.setAppName(appName);
        req.setStreamName(streamName);
        req.setDomainName(PUSH_DOMIAN_NAME);
        req.setResumeTime(FORMAT.format(new Date(currentTime)));
        ForbidLiveStreamResponse res = client.ForbidLiveStream(req);
        return res.getRequestId();
    }

    /**
     * 禁推 一个直播流
     * @param streamName
     *            直播流名字
     * @param resumeTime
     *            禁推时长（单位分钟）
     * @return 请求 ID
     * @throws TencentCloudSDKException
     */
    public String forbidLiveStream(String streamName, Long resumeTime) throws TencentCloudSDKException {
        return forbidLiveStream(streamName, APP_NAME, resumeTime);
    }

    /**
     * 禁推 一个直播流 禁推30分钟自动生成
     * @param streamName
     *            直播流名字
     * @return 请求 ID
     * @throws TencentCloudSDKException
     */
    public String forbidLiveStream(String streamName) throws TencentCloudSDKException {
        return forbidLiveStream(streamName, APP_NAME, 30L);
    }

    /**
     * 查询禁播列表
     * @param pageNum
     *            页数1开始
     * @throws TencentCloudSDKException
     */
    public ForbidStreamInfo[] getForbidStreamList(long pageNum, long pageSize) throws TencentCloudSDKException {
        LiveClient client = getLiveClient();
        DescribeLiveForbidStreamListRequest req = new DescribeLiveForbidStreamListRequest();
        req.setPageNum(pageNum);
        req.setPageSize(pageSize);
        DescribeLiveForbidStreamListResponse res = client.DescribeLiveForbidStreamList(req);
        return res.getForbidStreamList();
    }

    /**
     * 恢复一个被禁止的直播流 全参数
     * @param streamName
     *            直播流名字
     * @param appName
     *            应用名称
     * @return 请求 ID
     * @throws TencentCloudSDKException
     */
    public String resumeLiveStream(String streamName, String appName) throws TencentCloudSDKException {
        LiveClient client = getLiveClient();
        ResumeLiveStreamRequest req = new ResumeLiveStreamRequest();
        req.setAppName(appName);
        req.setDomainName(PUSH_DOMIAN_NAME);
        req.setStreamName(streamName);
        ResumeLiveStreamResponse res = client.ResumeLiveStream(req);
        return res.getRequestId();
    }

    /**
     * 恢复一个被禁止的直播流
     * @param streamName
     *            直播流名字
     * @return 请求 ID
     * @throws TencentCloudSDKException
     */
    public String resumeLiveStream(String streamName) throws TencentCloudSDKException {
        return resumeLiveStream(streamName, APP_NAME);
    }

    /**
     * 暂停一个直播流 全参数
     *
     * @author hl128k
     * @param streamName
     *            直播流名字
     * @param appName
     *            应用名称
     * @return 请求 ID
     * @throws TencentCloudSDKException
     */
    public String dropLiveStream(String streamName, String appName) throws TencentCloudSDKException {
        LiveClient client = getLiveClient();
        DropLiveStreamRequest req = new DropLiveStreamRequest();
        req.setAppName(appName);
        req.setDomainName(PUSH_DOMIAN_NAME);
        req.setStreamName(streamName);
        DropLiveStreamResponse res = client.DropLiveStream(req);
        return res.getRequestId();
    }

    /**
     * 暂停一个直播流 全参数
     * @param streamName
     *            直播流名字
     * @return 请求 ID
     * @throws TencentCloudSDKException
     */
    public String dropLiveStream(String streamName) throws TencentCloudSDKException {
        return dropLiveStream(streamName, APP_NAME);
    }

    /**
     * 获取在线流的推流数据
     * @param pageNum
     * @param pageSize
     * @return
     * @throws TencentCloudSDKException
     */
    public PushDataInfo[] describeLiveStreamOnlineInfo(long pageNum, long pageSize, String appName, String pushDomain)
            throws TencentCloudSDKException {
        LiveClient client = getLiveClient();
        DescribeLiveStreamPushInfoListRequest req = new DescribeLiveStreamPushInfoListRequest();
        req.setPageNum(pageNum);
        req.setPageSize(pageSize);
        req.setAppName(appName);
        req.setPushDomain(pushDomain);
        DescribeLiveStreamPushInfoListResponse res = client.DescribeLiveStreamPushInfoList(req);
        return res.getDataInfoList();
    }

    /**
     * 查询播放数据，支持按流名称查询详细播放数据，也可按播放域名查询详细总数据
     * @param streamName
     *            直播流名字
     * @throws TencentCloudSDKException
     */
    public DayStreamPlayInfo[] describeStreamPlayInfoList(String streamName, String StartTime, String EndTime)
            throws TencentCloudSDKException {
        LiveClient client = getLiveClient();
        DescribeStreamPlayInfoListRequest req = new DescribeStreamPlayInfoListRequest();
        req.setStreamName(streamName);
        req.setStartTime(StartTime);
        req.setEndTime(EndTime);
        DescribeStreamPlayInfoListResponse res = client.DescribeStreamPlayInfoList(req);
        return res.getDataInfoList();
    }

    /**
     * 查询天维度每条流的播放数据，包括总流量等
     * @param DayTime
     *            日期， 格式：YYYY-mm-dd。
     * @throws TencentCloudSDKException
     */
    public PlayDataInfoByStream[] describeStreamDayPlayInfoList(String DayTime) throws TencentCloudSDKException {
        LiveClient client = getLiveClient();
        DescribeStreamDayPlayInfoListRequest req = new DescribeStreamDayPlayInfoListRequest();
        req.setDayTime(DayTime);
        DescribeStreamDayPlayInfoListResponse res = client.DescribeStreamDayPlayInfoList(req);
        return res.getDataInfoList();
    }
}
